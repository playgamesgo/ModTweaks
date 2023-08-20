import { Resourcepack } from '../pages/ResourcePacks';

const packSelectCallback = (
  selected: {[key: string]: (Resourcepack)[]},
  setSelected: (_selected: any) => void,
  category: string,
  pack: Resourcepack,
) => {
  if (selected[category]) {
    if (selected[category].filter((e) => e.name === pack.name).length) {
      const newSelected = { ...selected };
      newSelected[category] = newSelected[category].filter((e) => e.name !== pack.name);
      if (!newSelected[category].length) {
        delete newSelected[category];
      }

      setSelected(newSelected);
    } else {
      const newSelected = { ...selected };
      newSelected[category] = [...(newSelected[category] || []), pack];
      setSelected(newSelected);
    }
  } else {
    const newSelected = { ...selected };
    newSelected[category] = [pack];
    setSelected(newSelected);
  }
};

export default packSelectCallback;
