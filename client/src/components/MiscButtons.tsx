import React from 'react';
import { Icon } from '@iconify/react';

function MiscButtons() {
  return (
    <div className="flex flex-col gap-2">
      <a
        href="/installation"
        target="_blank"
        rel="noreferrer"
        className="w-full flex items-center justify-center bg-[#E99743] hover:bg-[#dd8a38] transition-all text-white rounded-md py-3 shadow-md"
      >
        How to Install
      </a>
    </div>
  );
}

export default MiscButtons;
