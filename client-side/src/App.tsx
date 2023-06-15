import React from "react";
import { Route, Routes } from "react-router-dom";
import { SearchBar } from "./components";
import { Home } from "./pages";
import { useSelector } from "react-redux";
import { RootState } from "./redux/store";

const App = () => {
  const signup = useSelector((state: RootState) => state.signup.username);
  console.log(signup);
  return (
    <div>
      <SearchBar />
      <Routes>
        <Route path="/" element={<Home />} />
      </Routes>
    </div>
  );
};

export default App;
