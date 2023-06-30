import React from "react";

import CreatePoll from "./responsive/app-community/CreatePoll";
import Community from "./responsive/app/Community";
import CommunityProfile from "./responsive/app-community-detail/CommunityProfile";
import { QueryClient, QueryClientProvider } from "react-query";

function Dashboard2() {
  const queryClient:QueryClient = new QueryClient();
  return (
    <div className="flex flex-row bg-slate-200 w-screen">
      <Community />
      <QueryClientProvider client={queryClient}>
      <CreatePoll />
      </QueryClientProvider>
     
      <CommunityProfile />
    </div>
  );
}

export default Dashboard2;
