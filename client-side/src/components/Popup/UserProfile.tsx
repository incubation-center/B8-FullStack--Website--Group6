import React from "react";
import { AiOutlineLogout } from "react-icons/ai";
import { HiOutlineUserCircle } from "react-icons/hi";
import { useNavigate } from "react-router-dom";

const Logout = () => {
  const navigate = useNavigate();
  const handleLogout = () => {
    navigate("/user/sign_in");
  };

  return (
    <div className="absolute z-100 top-10 right-2 shadow-lg w-64 rounded-md px-4">
      <div className="flex items-center justify-between h-12 bg-white hover:text-blue-custom border-b">
        <span>Profile</span>
        <HiOutlineUserCircle className="w-5 h-5" />
      </div>
      <div
        className="flex items-center justify-between h-12 bg-white hover:text-blue-custom"
        onClick={handleLogout}
      >
        <span>Logout</span>
        <AiOutlineLogout className="w-5 h-5" />
      </div>
    </div>
  );
};

export default Logout;
