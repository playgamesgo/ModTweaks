/* eslint-disable no-restricted-syntax */
/* eslint-disable guard-for-in */
import axios from 'axios';
import { Resourcepack } from '../pages/ResourcePacks';

const download = async (
  selected: { [key: string]: (Resourcepack)[] },
  type: string,
  version: number,
) => {
  const data: {
    [x: string]: (Resourcepack| string)[];
  } = { ...selected };

  for (const key in selected) {
    data[key] = selected[key].map((e) => e.name);
  }

  if (JSON.stringify(selected) !== '{}') {
    const { data: { link, status } } = await axios({
      url: `http://0.0.0.0:8080/download/${type}`,
      method: 'POST',
      data: {
        data,
        version,
      },
    });
    if (status === 'success') {
      const a = document.createElement('a');
      a.href = `https://vanillatweaks.net${link}`;
      a.download = link.split('/').pop();
      a.click();
    }
  }
};

export default download;
