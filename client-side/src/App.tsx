import React from "react";
import { Route, Routes } from "react-router-dom";
import { RegisterForm, LoginForm } from "./pages";
import Dashboard from "./homepage/Dashboard";

const App = () => {
  return (
    <div className="custom-bg-gray text-base">
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/user/sign_up" element={<RegisterForm />} />
        <Route path="/user/sign_in" element={<LoginForm />} />
      </Routes>
    </div>
  );
};

export default App;
