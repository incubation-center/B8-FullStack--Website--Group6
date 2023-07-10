import React, { useState, useEffect } from "react";
import Avatar from "../../../assets/Avatar.png";
import { FcPieChart } from "react-icons/fc";
import { AiOutlineDelete } from "react-icons/ai";
import api from "../../../utils/api";

function Poll1({
  createdBy,
  pollDate,
  options,
  pollQuestion,
  pollId,
  votedOn,
}: any) {
  // Handle options update
  const [newOption, setNewOption] = useState(options);
  // Option Click handler
  const handleOptionClick = async (optionId: number) => {
    const accessToken = localStorage.getItem("accessToken");
    const headers = {
      Authorization: `${accessToken}`,
    };
    const body = {
      option_id: [optionId],
    };
    try {
      const response = await api.post(`/vote/poll/${pollId}`, body, {
        headers,
      });
      if (response.status === 200) {
        setNewOption(response.data.options);
        alert("Voted Successfully!");
        window.location.reload();
      }
    } catch (error) {
      alert("You can only vote once per poll!");
    }
  };

  return (
    <div className="poll1 flex flex-col border h-fit bg-white rounded-md">
      <div className=" userChart flex justify-between items-center">
        <div className="User flex mt-5 ml-5 relative">
          <img
            src={Avatar}
            alt="Profile 1"
            className="w-10 h-10 rounded-full mr-2 border-2 border-blue-500"
          />
          <h5 className="text-sm">
            <span className="bottom-1 left-8 absolute w-3 h-3 bg-green-400 border-2 border-white dark:border-gray-800 rounded-full"></span>
            {createdBy} <br />
            <h4>{pollDate}</h4>
          </h5>
        </div>
        <FcPieChart className="mr-5 mt-5 w-10 h-10" />
      </div>
      <p className="mt-5 ml-5 font-light text-[15px] md:text-[17px]">
        {pollQuestion}
      </p>
      <div className="food-menu grid grid-cols-1 md:grid-cols-1 lg:grid-cols-2 mr-5 ml-5 gap-4 mt-5 text-gray-800">
        {newOption.map((option: any) => {
          return (
            <div
              key={option.id}
              onClick={() => handleOptionClick(option.id)}
              className={`text-[15px] font-sans font-bold border border-neutral-300 shadow px-5 py-3 rounded-xl hover:bg-[#2D9CDB] hover:cursor-pointer ${
                votedOn === option.id && "bg-[#2D9CDB]"
              }`}
            >
              {option.optionText}
            </div>
          );
        })}
      </div>
      <div className="progress-bar  mt-7 ml-5 mr-5">
        {options.map((option: any) => {
          return (
            <>
              <div
                key={option.id}
                className="mb-1 text-base font-medium dark:text-white flex justify-between items-center"
              >
                <h1 className="text-[12px] md:text-[15px]">
                  {option.optionText}
                </h1>
                <h1 className="text-[12px] text-gray-500 md:text-[15px]">
                  {option.percentage * 100} %
                </h1>
              </div>
              <div className="w-full bg-gray-100 rounded-full h-2 mb-4 dark:bg-gray-100">
                <div className="w-3 bg-sky-500 h-full rounded-full dark:bg-blue-500"></div>
              </div>
            </>
          );
        })}
        {/* <div className="mb-1 text-base font-medium dark:text-white flex justify-between items-center">
          <h1 className="text-[12px] md:text-[15px]">Solor Korko</h1>
          <h1 className="text-[12px] md:text-[15px]">0%</h1>
        </div>
        <div className="w-full bg-gray-100 rounded-full h-2 mb-4 dark:bg-gray-100">
          <div className="w-3 bg-sky-500 h-full rounded-full dark:bg-blue-500"></div>
        </div> */}
        {/* <div className="mb-1 text-base font-medium dark:text-white flex justify-between items-center">
          <h1 className="text-[12px] md:text-[15px]">Kangkep Boak</h1>
          <h1 className="text-[12px] md:text-[15px]">0%</h1>
        </div>
        <div className="w-full bg-gray-100 rounded-full h-2 mb-4 dark:bg-gray-100">
          <div className="w-3 bg-sky-500 h-full rounded-full dark:bg-blue-500"></div>
        </div>
        <div className="mb-1 text-base font-medium dark:text-white flex justify-between items-center">
          <h1 className="text-[12px] md:text-[15px]">Khmer Curry</h1>
          <h1 className="text-[12px] md:text-[15px]">0%</h1>
        </div>
        <div className="w-full bg-gray-100 rounded-full h-2 mb-4 dark:bg-gray-100">
          <div className="w-3 bg-sky-500 h-full rounded-full dark:bg-blue-500"></div>
        </div>
        <div className="mb-1 text-base font-medium dark:text-white flex justify-between items-center">
          <h1 className="text-[12px] md:text-[15px]">Fried Chicken</h1>
          <h1 className="text-[12px] md:text-[15px]">0%</h1>
        </div>
        <div className="w-full bg-gray-100 rounded-full h-2 mb-4 dark:bg-gray-100">
          <div className="w-3 bg-sky-500 h-full rounded-full dark:bg-blue-500"></div>
        </div> */}
      </div>
      {/* <div className="btn-delete flex justify-end items-center mb-5 mr-5">
        <button className="bg-red-500 text-white font-sans  text-[13px] py-2 px-2 rounded-full flex items-center gap-x-1 w-fit lg:text-[17px] lg:px-3">
          <AiOutlineDelete style={deleteBtnIcon} />
          <span>Delete</span>
        </button>
      </div> */}
    </div>
  );
}

export default Poll1;
