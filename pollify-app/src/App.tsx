import React from "react";
import { Route, Routes } from "react-router-dom";
import { Home, RegisterForm } from "./pages";
import { useSelector } from "react-redux";
import { RootState } from "./redux/store";

const App = () => {
  const signup = useSelector((state: RootState) => state.register);
  return (
    <div className="custom-bg-gray text-base">
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/" element={<RegisterForm />} />
      </Routes>
    </div>
  );
};

export default App;
