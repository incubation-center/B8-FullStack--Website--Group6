import React from "react";
import { Route, Routes } from "react-router-dom";
import { Home, RegisterForm } from "./pages";
import { useSelector } from "react-redux";
import { RootState } from "./redux/store";
import { Home } from "./pages";
import Dashboard from "./homepage/Dashboard";
import SignInForm from "./pages/LoginPage";

const App = () => {
  const signup = useSelector((state: RootState) => state.register);
  return (
    <div className="custom-bg-gray text-base">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/homepage" element={<Dashboard />} />
        <Route path="/sign_up" element={<RegisterForm />} />
        <Route path="/sign_in" element={<SignInForm />} />
      </Routes>
    </div>
  );
};

export default App;
