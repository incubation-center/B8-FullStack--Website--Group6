import React from "react";
import { Route, Routes } from "react-router-dom";
import { RegisterForm, LoginForm } from "./pages";
import Community from "./homepage/responsive/app/Community";
import CreatePoll from "./homepage/responsive/app-community/CreatePoll";
import CommunityProfile from "./homepage/responsive/app-community-detail/CommunityProfile";
import Dashboard2 from "./homepage/Dashboard2";

const App = () => {
  return (
    <div className="custom-bg-gray text-base">
      <Routes>
        <Route path="/sign_up" element={<RegisterForm />} />
        <Route path="/sign_in" element={<LoginForm />} />
        <Route path="/responsive" element={<Community />} />
        <Route path="/createpoll" element={<CreatePoll />} />
        <Route path="/communitydetail" element={<CommunityProfile />} />
        <Route path="/dashboard2" element={<Dashboard2 />} />
      </Routes>
    </div>
  );
};

export default App;
