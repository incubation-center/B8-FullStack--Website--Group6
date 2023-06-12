import React from "react";
import { Route, Routes } from "react-router-dom";
import { SearchBar } from "./components";
import { Home } from "./pages";
import Dashboard from "./homepage/Dashboard";

const App = () => {
  return (
    <div>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/homepage" element={<Dashboard />} />
      </Routes>
    </div>
  );
};

export default App;
