import React from 'react';
import { Routes, Route, useLocation } from 'react-router-dom';
import Navbar from './components/Navbar';
import HowToInstall from './pages/HowToInstall';
import ResourcePack from './pages/ResourcePacks';
import Footer from './components/Footer';

function App() {
  const location = useLocation();
  return (
    <div
      className={`App w-full ${
        ['/installation'].includes(
          location.pathname,
        )
          ? 'lg:min-h-screen'
          : 'lg:h-screen'
      } flex flex-col relative`}
    >
      <Navbar />
      <Routes>
        <Route path="/" element={<ResourcePack />} />
        <Route path="/installation" element={<HowToInstall />} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
