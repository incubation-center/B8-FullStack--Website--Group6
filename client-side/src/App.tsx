import React from "react";
import {
  // BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import { RegisterForm, OtpVerification, LoginForm } from "./pages";
import Dashboard from "./homepage/Dashboard";
import Community from "./homepage/responsive/app/Community";
import CommunityProfile from "./homepage/responsive/app-community-detail/CommunityProfile";
import CreatePollPopup from "./homepage/popup/CreatePollPopup";

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/user/sign_in" replace />} />
      <Route path="/community" element={<Dashboard />} />
      <Route path="/community/:id" element={<Dashboard />} />
      <Route path="/user/sign_up" element={<RegisterForm />} />
      <Route path="/user/sign_in" element={<LoginForm />} />
      <Route path="/responsive" element={<Community />} />
      <Route path="/communitydetail" element={<CommunityProfile />} />
      <Route path="/test" element={<CreatePollPopup />} />
      <Route path="/auth/verification" element={<OtpVerification />} />
    </Routes>
  );
};

export default App;
