import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import { RegisterForm, LoginForm } from "./pages";
import { CreateCommunity } from "./components";
import Dashboard from "./homepage/Dashboard";

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/user/sign_in" replace />} />
      <Route path="/home" element={<Dashboard />} />
      <Route path="/user/sign_up" element={<RegisterForm />} />
      <Route path="/user/sign_in" element={<LoginForm />} />
      <Route path="/create/community" element={<CreateCommunity />} />
    </Routes>
  );
};

export default App;
