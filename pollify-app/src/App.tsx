import React from "react";
import { Route, Routes } from "react-router-dom";
import { Home } from "./pages";
import Dashboard from "./homepage/Dashboard";
import SignInForm from "./pages/LoginPage";

const App = () => {
  const signup = useSelector((state: RootState) => state.signup.username);
  console.log(signup);
  return (
    <div>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/homepage" element={<Dashboard />} />
        <Route path="/sign_in" element={<SignInForm />} />
      </Routes>
    </div>
  );
};

export default App;
