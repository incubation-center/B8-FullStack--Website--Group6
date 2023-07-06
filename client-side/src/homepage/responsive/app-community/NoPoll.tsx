import React from "react";
import WelcomeIcon from "../../../assets/icons/welcome.svg";
import Avatar from "../../../assets/Avatar.png";
import { FcPieChart } from "react-icons/fc";
import { AiOutlineDelete } from "react-icons/ai";

const communityName = "Moringa";

const NoPoll = () => {
  return (
    <div className="flex flex-col justify-center items-center ml-5 mr-6 h-[95%] border bg-white rounded-md">
      <div className="w-36 h-36 mb-3">
        <img className="w-full h-full" src={WelcomeIcon} alt="Welcome" />
      </div>
      <h1 className="font-bold text-2xl">
        Welcome to {communityName} Community!
      </h1>
      <small>This is the beginning of this community. Create poll now.</small>
    </div>
  );
};

export default NoPoll;
