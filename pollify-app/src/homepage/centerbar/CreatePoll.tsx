import React from "react";
import { AiFillTrophy } from "react-icons/ai";
import Poll1 from "./Poll1";

const trophy = {
  color: "blue", // Customize color
  fontSize: "70px", // Customize size
  opacity: 0.7,
};

function CreatePoll() {
  return (
    <>
      <div className="flex flex-col w-full overflow-y-auto overflow-x-hidden h-auto">
        <div className="bg-white">
          <h1 className="text-xl font-bold mt-5 mb-11 w-full ml-10">
            Welcome to the pitCool bro TED!
          </h1>
        </div>
        <div className="create-poll w-full flex mt-5 flex-row justify-around items-center gap-x-3">
          <div className="relative w-full ml-10 flex flex-grow">
            <div className="w-full absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
              <svg
                aria-hidden="true"
                className="w-5 h-5 text-gray-500 dark:text-gray-400"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
                ></path>
              </svg>
            </div>
            <input
              type="text"
              placeholder="Search community"
              className="py-4 pl-10 pr-4 border-2 border-gray-300 w-full bg-slate-200 rounded-full focus:outline-none focus:border-blue-500"
            />
          </div>

          <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold whitespace-nowrap rounded-full px-6 py-4">
            Create Poll
          </button>
          <AiFillTrophy
            className="mr-10 border-2 border-blue-500 rounded-full p-1"
            style={trophy}
          />
        </div>
        <Poll1 />
      </div>
    </>
  );
}

export default CreatePoll;
