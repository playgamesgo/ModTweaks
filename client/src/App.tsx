import React from 'react';
import { Routes, Route, useLocation } from 'react-router-dom';
import Home from './pages/Home';
import Navbar from './components/Navbar';
import HowToInstall from './pages/HowToInstall';
import ResourcePack from './pages/ResourcePacks';
import Datapacks from './pages/Datapacks';
import CraftingTweaks from './pages/CraftingTweaks';
import Footer from './components/Footer';
import About from './pages/About';

function App() {
  const location = useLocation();
  return (
    <div
      className={`App w-full ${
        ['/installation', '/about'].includes(location.pathname)
          ? 'lg:min-h-screen'
          : 'lg:h-screen'
      } flex flex-col relative`}
    >
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/installation" element={<HowToInstall />} />

        <Route path="/resource-packs" element={<ResourcePack />} />
        <Route path="/datapacks" element={<Datapacks />} />
        <Route path="/crafting-tweaks" element={<CraftingTweaks />} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
