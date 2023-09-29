package me.playgamesgo.modtweaks;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class PackGenerator {
    public static JSONObject packGenerator(String jsonData) {
        String category;
        JSONArray filesArray;
        int version;
        ArrayList<String> modUrls = new ArrayList<>();
        ArrayList<String> additionalFiles = new ArrayList<>();
        additionalFiles.add("fabric-api");

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonData);

            JSONObject dataObject = (JSONObject) jsonObject.get("data");

            version = Integer.parseInt(jsonObject.get("version").toString());

            addAdditionalFiles(dataObject, additionalFiles);

            jsonData = jsonObject.toJSONString();

            for (Object key : dataObject.keySet()) {
                category = (String) key;
                filesArray = (JSONArray) dataObject.get(category);

                for (Object element : filesArray) {
                    try {
                        String modVersion;

                        switch (version) {
                            case 19:
                                modVersion = "1.19.2";
                                break;
                            case 20:
                                modVersion = "1.20.1";
                                break;
                            default:
                                Main.logger.warning("Unknown version: " + version);
                                return jsonObject;
                        }

                        String loadersParam = URLEncoder.encode("[\"fabric\"]", StandardCharsets.UTF_8);
                        String gameVersionsParam = URLEncoder.encode("[\"" + modVersion + "\"]", StandardCharsets.UTF_8);
                        String modName = URLEncoder.encode(element.toString(), StandardCharsets.UTF_8);

                        JSONArray jsonArray = getJsonArray(loadersParam, gameVersionsParam, modName);

                        for (Object fileElement : jsonArray) {
                            JSONObject jsonFilesObject = (JSONObject) fileElement;
                            JSONArray modFilesArray = (JSONArray) jsonFilesObject.get("files");
                            if (!modFilesArray.isEmpty()) {
                                JSONObject fileObject = (JSONObject) modFilesArray.get(0);
                                String urlValue = (String) fileObject.get("url");
                                modUrls.add(urlValue);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        Main.logger.warning("Error while getting modrinth data: " + e.getMessage());
                    }
                }
            }

        } catch (ParseException e) {
            Main.logger.warning("Error while parsing json: " + jsonData + "\n" + e.getMessage());
        }

        try {
            Path tempDir = Files.createTempDirectory("temp-download");

            for (String url : modUrls) {
                downloadFile(url, tempDir);
            }

            String customDir = "client/public/zips";
            Date date = new Date();
            String zipFileName = "modpack-" + date.getTime() + ".zip";
            createZipArchive(tempDir, customDir, zipFileName);

            deleteFilesInDirectory(tempDir);

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("link", "/zips/" + zipFileName);
            jsonResponse.put("status", "success");

            String jsonResult = JSONValue.toJSONString(jsonResponse);

            return jsonResponse;
        } catch (IOException e) {
            Main.logger.warning("Error while creating zip archive: " + e.getMessage());
        }
        return null;
    }

    private static void addAdditionalFiles(JSONObject dataObject, ArrayList<String> additionalFiles) {
        for (Object key : dataObject.keySet()) {
            String category = (String) key;
            JSONArray filesArray = (JSONArray) dataObject.get(category);

            JSONArray additionalFilesArray = new JSONArray();
            additionalFilesArray.addAll(additionalFiles);

            filesArray.addAll(additionalFilesArray);

            dataObject.put(category, filesArray);

            break;
        }
    }

    private static JSONArray getJsonArray(String loadersParam, String gameVersionsParam, String modName) throws IOException, ParseException {
        String apiUrl = "https://api.modrinth.com/v2/project/" + modName + "/version?loaders=" + loadersParam + "&game_versions=" + gameVersionsParam;

        URL url = new URL(apiUrl);
        URLConnection connection = url.openConnection();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(response.toString());
        return jsonArray;
    }

    private static void downloadFile(String urlStr, Path targetDirectory) throws IOException {
        URL url = new URL(urlStr);
        String fileName = urlStr.substring(urlStr.lastIndexOf('/') + 1);
        Path targetFile = targetDirectory.resolve(fileName);

        try (InputStream in = url.openStream()) {
            Files.copy(in, targetFile, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static void createZipArchive(Path sourceDirectory, String customDir, String zipFileName) throws IOException {
        Path customDirPath = Paths.get(customDir);
        if (!Files.exists(customDirPath)) {
            Files.createDirectories(customDirPath);
        }

        Path zipFilePath = customDirPath.resolve(zipFileName);

        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath.toString()))) {
            Files.walk(sourceDirectory)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        try {
                            String entryName = sourceDirectory.relativize(path).toString();
                            ZipEntry zipEntry = new ZipEntry(entryName);
                            zipOut.putNextEntry(zipEntry);
                            Files.copy(path, zipOut);
                            zipOut.closeEntry();
                        } catch (IOException e) {
                            Main.logger.warning("Error while creating zip archive: " + e.getMessage());
                        }
                    });
        }
    }

    private static void deleteFilesInDirectory(Path directory) throws IOException {
        Files.walk(directory)
                .filter(path -> !Files.isDirectory(path))
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        Main.logger.warning("Error while deleting files: " + e.getMessage());
                    }
                });
    }
}