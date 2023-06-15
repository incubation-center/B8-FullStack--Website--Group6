import React from "react";
import { FcPieChart } from "react-icons/fc";
import { AiFillCheckCircle } from "react-icons/ai";
import Avatar from "../../assets/Avatar.png";

const selectFoodIcons = {
  color: "blue",
  opacity: 0.6,
};

function SelectFood() {
  return (
    <div className="select-food flex flex-col mt-10 ml-10 mr-10 border h-fit bg-white rounded-md">
      <div className=" userChart flex justify-between items-center">
        <div className="User flex mt-5 ml-5 relative">
          <img
            src={Avatar}
            alt="Profile 1"
            className="w-16 h-16 rounded-full mr-2 border-2 border-blue-500"
          />
          <h1>
            <span className="bottom-0 left-11 absolute  w-5 h-5 bg-green-400 border-2 border-white dark:border-gray-800 rounded-full"></span>
            Ted <br />
            <h4>21 Jul</h4>
          </h1>
        </div>
        <FcPieChart className="mr-5 mt-5 w-10 h-10" />
      </div>
      <p className="mt-5 ml-5 text-[20px] font-light">
        here is the new menus for this lunch. <br />
        Please vote for 3 food options.
      </p>
      <div className="food-options flex justify-center items-center">
        <div className="border border-neutral-300 w-1/2 ml-5 mt-5 px-6 py-3 flex items-center gap-x-2 rounded-xl shadow">
          <div className="radio w-6 h-6 border border-gray-300 rounded-full"></div>
          <h1 className="font-medium">BBQ</h1>
        </div>
        <div className="border border-neutral-300 w-1/2 ml-5 mt-5 px-6 py-3 bg-sky-200 flex items-center gap-x-2 mr-5 rounded-xl shadow">
          <AiFillCheckCircle className="w-7 h-7" style={selectFoodIcons} />
          <h1 className="font-medium">Khmer Noddle</h1>
        </div>
      </div>
      <div className="food-options flex justify-center items-center">
        <div className="border border-neutral-300 w-1/2 ml-5 mt-5 px-6 py-3 bg-sky-200 flex items-center gap-x-2 rounded-xl shadow">
          <AiFillCheckCircle className="w-7 h-7" style={selectFoodIcons} />
          <h1 className="font-medium">Salt Egg Boiled</h1>
        </div>
        <div className="border border-neutral-300 w-1/2 ml-5 mt-5 px-6 py-3 flex items-center gap-x-2 mr-5 rounded-xl shadow">
          <div className="radio w-6 h-6 border border-gray-300 rounded-full"></div>
          <h1 className="font-medium">Spaghetti</h1>
        </div>
      </div>
      <div className="progress-bar mt-5 ml-5 mr-5">
        <div className="mb-1 text-base font-medium dark:text-white flex justify-between items-center">
          <h1>Please Vote</h1>
          <h1 className="text-sky-200 font-bold">2/3</h1>
        </div>
        <div className="w-full bg-gray-100 rounded-full h-1.5 mb-4 dark:bg-gray-700">
          <div className="w-5/6 bg-sky-500 h-1.5 rounded-full dark:bg-blue-500"></div>
        </div>
      </div>
    </div>
  );
}

export default SelectFood;
