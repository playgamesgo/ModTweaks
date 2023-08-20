import { Icon } from "@iconify/react";
import React, { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";

function Navbar() {
  const location = useLocation();
  const [navOpen, setNavOpen] = useState(false);

  useEffect(() => {
    setNavOpen(false);
  }, [location]);

  return (
    <>
      <nav className="w-full p-6 bg-neutral-700 shadow-md flex items-center justify-between fixed z-[9999]">
        <Link
          to="/"
          className="flex items-center gap-3 uppercase text-white tracking-widest text-lg font-semibold"
        >
          <img
            src="https://vanillatweaks.net/assets/images/logo.png"
            alt="logo"
            className="w-8 h-8"
          />
          <span className="mt-0.5">Mod Tweaks</span>
        </Link>
        <button
          type="button"
          className="lg:hidden"
          onClick={() => setNavOpen(!navOpen)}
        >
          <Icon icon="uil:bars" className="text-3xl text-white" />
        </button>
      </nav>
    </>
  );
}

export default Navbar;
