import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../../redux/store";
import PolliFy from "../../../assets/PolliFy.png";
import Avatar from "../../../assets/Avatar.png";
import { MdTranslate } from "react-icons/md";
import { IoMdNotificationsOutline } from "react-icons/io";
import { AiOutlineSearch } from "react-icons/ai";
import { BsFillPlusCircleFill } from "react-icons/bs";
import AddedFavorite from "./AddedFavorite";
import UserCommunity from "./UserCommunity";
import { openCreateCommunity } from "../../../redux/slices/Community";
import CreateCommunity from "../../popup/CreateCommunity";
import { QueryClient, QueryClientProvider } from "react-query";

function Community() {
  const dispatch = useDispatch();
  const { isCreateCommunityOpen } = useSelector(
    (state: RootState) => state.createCommunity
  );

  const queryClient: QueryClient = new QueryClient();
  const [searchQuery, setSearchQuery] = useState<string>("");
  const handleCreateCommunity = () => {
    dispatch(openCreateCommunity());
  };

  return (
    <div className=" bg-white gap-y-8 h-screen lg:w-2/6 hidden lg:flex lg:flex-col">
      <div className="logo-profile flex justify-between items-center mt-5 ml-5">
        <img src={PolliFy} alt="Profile 1" className="logo w-fit h-7" />
        <div className="translate flex gap-x-3 items-center lg:hidden">
          <MdTranslate className="w-6 h-6" />
          <IoMdNotificationsOutline className="w-6 h-6" />
          <div className="relative">
            <img
              src={Avatar}
              alt="Profile 1"
              className="w-8 h-8 rounded-full mr-2 border-2 border-blue-custom"
            />
            <span className="bottom-0 left-7 absolute  w-3.5 h-3.5 bg-green-400 border-2 border-white dark:border-gray-800 rounded-full"></span>
          </div>
        </div>
      </div>
      <div className="line border border-gray-200 ml-5 mr-5 lg:hidden"></div>
      <div className="search-community-field relative mr-5 flex ml-5">
        <div className="w-full absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
          <AiOutlineSearch className="text-gray-400 w-5 h-5" />
        </div>
        <input
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          type="text"
          placeholder="Search community"
          className="py-2 px-4 pl-9 border-2 border-gray-300 w-full rounded-full focus:outline-none focus:border-blue-500"
        />
      </div>
      <div className="create-community flex items-center gap-x-3 ml-5 mr-5">
        <button
          className="cursor-pointer hover:opacity-70"
          type="button"
          onClick={handleCreateCommunity}
        >
          <BsFillPlusCircleFill className="w-7 h-7 text-blue-custom" />
        </button>
        <h1>Create Community</h1>
      </div>
      {isCreateCommunityOpen && <CreateCommunity />}
      <h1 className="ml-5">Favorite</h1>
      <AddedFavorite />
      <h1 className="ml-5">Your Community</h1>
      <QueryClientProvider client={queryClient}>
        <UserCommunity searchQuery={searchQuery} />
      </QueryClientProvider>
    </div>
  );
}

export default Community;
