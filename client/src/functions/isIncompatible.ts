import { Resourcepack } from '../pages/ResourcePacks';

function isIncompatible(
  selected: {[key: string]: (Resourcepack)[]},
  pack: Resourcepack,
) {
  return Object.values(selected).flat().some((e) => e.incompatible.includes(pack.name));
}

export default isIncompatible;
