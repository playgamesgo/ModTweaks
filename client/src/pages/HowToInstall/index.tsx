/* eslint-disable max-len */
import React from 'react';
import {Link} from "react-router-dom";
//import guide1 from "./recources/images/1.png";

function HowToInstall() {
  return (
    <div className="flex pt-32 installation text-white justify-center w-full tracking-wide text-lg mt-20">
      <div className="w-8/12">
        <h1 className="font-medium text-5xl uppercase tracking-[0.2em] text-center mb-4">
          Installation Process
        </h1>
        <h1
          className="mt-24 text-3xl uppercase tracking-[0.2em] font-medium"
        >
          Mod Packs
        </h1>
        <ol className="install">
          <li> Download the <Link
              to="https://fabricmc.net/use/installer/"
              className="underline decoration-[1.5px] hover:text-[#E99743] transition-all">
            Fabric Installer
          </Link>. Go to this <Link
              to="https://fabricmc.net/use/installer/"
              className="underline decoration-[1.5px] hover:text-[#E99743] transition-all">
            link
          </Link> and download the Universal JAR installer or the Windows .exe installer, depending on your preference. </li>
          <img
            src="https://vanillatweaks.net/installation/img/mp/1.png"
            className="img-fluid"
            alt=""
          />
          <li>Download the JAR/Universal installer.</li>
          <img
            src="https://vanillatweaks.net/installation/img/mp/2.png"
            className="img-fluid"
            alt=""
          />
          <li>
            Once downloaded, run the installer.
          </li>
          <img
            src="https://vanillatweaks.net/installation/img/mp/3.png"
            className="img-fluid"
            alt=""
          />
          <li>
            Drag and drop your downloaded &quot;Vanilla Tweaks&quot; zip into
            the folder that was opened.
          </li>
          <img
            src="https://vanillatweaks.net/installation/img/rp/4.png"
            className="img-fluid"
            alt=""
          />
          <li>
            You will now see the resource pack in the list upon reloading the
            Resource Pack Screen.
          </li>
        </ol>
      </div>
    </div>
  );
}

export default HowToInstall;
