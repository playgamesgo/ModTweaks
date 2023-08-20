import React from "react";
import { Icon } from "@iconify/react";
import { Link } from "react-router-dom";

function Footer() {
  return (
    <div className="w-full shadow-[0_-4px_6px_-1px_rgb(0_0_0/0.1)] p-4 px-8 flex flex-col lg:flex-row items-center justify-between tracking-wide bg-neutral-700 lg:absolute bottom-0 left-0 translate-y-full text-white gap-2">
      <span className="sm:hidden">Copyright © Vanilla Tweaks 2021</span>
      <p className="flex items-center gap-2">
        <span className="hidden sm:flex items-center gap-2">
          Copyright © Mod Tweaks 2023
        </span>
      </p>
      <p className="flex items-center gap-2">
        <span className="hidden sm:flex items-center gap-2">
          <Link
            to="https://github.com/playgamesgo/ModTweaks/issues"
            className="underline decoration-[1.5px] hover:text-[#E99743] transition-all"
          >
            Send us feedback
          </Link>
        </span>
      </p>
      <span className="sm:hidden">
        <Link
          to="/feedback"
          className="underline decoration-[1.5px] hover:text-[#E99743] transition-all"
        >
          Send us feedback
        </Link>
      </span>
    </div>
  );
}

export default Footer;
