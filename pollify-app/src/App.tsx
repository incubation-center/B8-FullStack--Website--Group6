import React from "react";
import { Route, Routes } from "react-router-dom";
import { Home } from "./pages";
import SignInForm from "./pages/LoginPage";

const App = () => {
  return (
    <div>
      <Routes>
        <Route path="/" element={<SignInForm />} />
      </Routes>
    </div>
  );
};

export default App;
