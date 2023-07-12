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
import ProtectedRoute from "./ProtectedRoute";
import Alert from "./components/Popup/Alert";

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/user/sign_in" replace />} />
      <Route path="/user/sign_up" element={<RegisterForm />} />
      <Route path="/user/sign_in" element={<LoginForm />} />
      <Route path="/auth/verification" element={<OtpVerification />} />
      <Route
        path="/community"
        element={
          <ProtectedRoute>
            <Dashboard />
          </ProtectedRoute>
        }
      />
      <Route
        path="/community/:id"
        element={
          <ProtectedRoute>
            <Dashboard />
          </ProtectedRoute>
        }
      />

      <Route
        path="/responsive"
        element={
          <ProtectedRoute>
            <Community />
          </ProtectedRoute>
        }
      />
      <Route
        path="/communitydetail"
        element={
          <ProtectedRoute>
            <CommunityProfile />
          </ProtectedRoute>
        }
      />
      <Route
        path="/test"
        element={
          <ProtectedRoute>
            <Alert
              variant="error"
              message={"You can only vote once!"}
              showAlert={false}
            />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
};

export default App;
