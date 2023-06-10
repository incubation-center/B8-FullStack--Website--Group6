import React from "react";
import { Route, Routes } from "react-router-dom";
import { SearchBar } from "./components";
import { Home } from "./pages";
import Navbar from "./homepage/Navbar";

const App = () => {
  return (
    <div>
      <SearchBar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/homepage" element={<Navbar />} />
      </Routes>
    </div>
  );
};

export default App;
