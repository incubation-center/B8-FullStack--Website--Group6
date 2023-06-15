import React from "react";
import { FcPieChart } from "react-icons/fc";
import { AiOutlineDelete } from "react-icons/ai";
import Poll2 from "./Poll2";
import SelectFood from "./SelectFood";
import Rating from "./Rating";
import Avatar from "../../assets/Avatar.png";

function Poll1() {
  return (
    <>
      <div className="poll1 flex flex-col ml-10 mt-5 mr-10 border h-fit bg-white rounded-md">
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
        <div className="food-options flex justify-center items-center mr-5">
          <div className="font-medium border border-neutral-300 shadow w-1/2 ml-5 mt-5 px-6 py-4 rounded-xl">
            Somlor Korko
          </div>
          <div className="font-medium border border-neutral-300 shadow w-1/2 ml-5 mt-5 px-6 py-4 rounded-xl">
            Kangkep Boak
          </div>
        </div>
        <div className="food-options flex justify-center items-center mr-5">
          <div className="font-medium border border-neutral-300 shadow w-1/2 ml-5 mt-5 px-6 py-4 rounded-xl">
            Khmer Curry
          </div>
          <div className="font-medium border border-neutral-300 shadow w-1/2 ml-5 mt-5 px-6 py-4 rounded-xl">
            Fried Chicken
          </div>
        </div>
        <div className="progress-bar mt-5 ml-5 mr-5">
          <div className="mb-1 text-base font-medium dark:text-white flex justify-between items-center">
            <h1>Solor Korko</h1>
            <h1>0%</h1>
          </div>
          <div className="w-full bg-gray-100 rounded-full h-1.5 mb-4 dark:bg-gray-700">
            <div className="w-1 bg-sky-500 h-1.5 rounded-full dark:bg-blue-500"></div>
          </div>
          <div className="mb-1 text-base font-medium dark:text-white flex justify-between items-center">
            <h1>Kangkep Boak</h1>
            <h1>0%</h1>
          </div>
          <div className="w-full bg-gray-100 rounded-full h-1.5 mb-4 dark:bg-gray-700">
            <div className="w-1 bg-sky-500 h-1.5 rounded-full dark:bg-blue-500"></div>
          </div>
          <div className="mb-1 text-base font-medium dark:text-white flex justify-between items-center">
            <h1>Khmer Curry</h1>
            <h1>0%</h1>
          </div>
          <div className="w-full bg-gray-100 rounded-full h-1.5 mb-4 dark:bg-gray-700">
            <div className="w-1 bg-sky-500 h-1.5 rounded-full dark:bg-blue-500"></div>
          </div>
          <div className="mb-1 text-base font-medium dark:text-white flex justify-between items-center">
            <h1>Fried Chicken</h1>
            <h1>0%</h1>
          </div>
          <div className="w-full bg-gray-100 rounded-full h-1.5 mb-4 dark:bg-gray-700">
            <div className="w-1 bg-sky-500 h-1.5 rounded-full dark:bg-blue-500"></div>
          </div>
        </div>
        <div className="btn-delete flex justify-end items-center mb-5 mr-5">
          <button className="bg-red-500 text-white py-2 px-4 rounded-full flex items-center gap-x-1 w-fit">
            <AiOutlineDelete className="bg-white-300" />
            <span>Delete</span>
          </button>
        </div>
      </div>
      <Poll2 />
      <SelectFood />
      <Rating />
    </>
  );
}

export default Poll1;
