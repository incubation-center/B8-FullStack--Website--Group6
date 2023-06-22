import React from "react";
import Logo from "./sidebar/Logo";
import CommunityProfile from "./rightSideba/CommunityProfile";
import CreatePoll from "./centerbar/CreatePoll";

function Dashboard() {
  return (
    <div className="flex flex-row justify-between bg-slate-200">
      <Logo />
      <CreatePoll />
      <CommunityProfile />
    </div>
  );
}

export default Dashboard;
