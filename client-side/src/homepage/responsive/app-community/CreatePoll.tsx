import React, { useState, useEffect } from "react";
import Avatar from "../../../assets/Avatar.png";
import { MdTranslate } from "react-icons/md";
import { IoMdNotificationsOutline } from "react-icons/io";
import { AiOutlineSearch } from "react-icons/ai";
import Poll1 from "./Poll1";
import Poll2 from "./Poll2";
import SelectFood from "./SelectFood";
import Rating from "./Rating";
import { RootState } from "../../../redux/store";
import { useNavigate } from "react-router-dom";
import CreatePollPopup from "../../popup/CreatePollPopup";
import { useDispatch, useSelector } from "react-redux";
import { openCreatePollPopup } from "../../../redux/slices/CreatePoll";
import PolliFy from "../../../assets/PolliFy.png";
import { NoPoll } from "../../../homepage";
import TrophyIcon from "../../../assets/icons/trophy.svg";
import { Poll } from "../../../types/redux/create_poll";
import api from "../../../utils/api";

function CreatePoll() {
  const dispatch = useDispatch();
  const isCreatePollPopupOpen = useSelector(
    (state: RootState) => state.createPoll.isCreatePollPopupOpen
  );

  // fetched poll state
  const [polls, setPolls] = useState<Poll[]>([]);

  const { username } = useSelector((state: RootState) => state.userCommunity);
  const { community } = useSelector((state: RootState) => state.userCommunity);
  const communityId = localStorage.getItem("communityId");

  const navigate = useNavigate();
  const handleClick = () => {
    navigate("/communitydetail");
  };

  const handleCreatePoll = () => {
    console.log("Create Poll Clicked");
    dispatch(openCreatePollPopup());
  };

  useEffect(() => {
    const fetchPolls = async () => {
      const accessToken = localStorage.getItem("accessToken");
      const headers = {
        Authorization: `${accessToken}`,
      };
      try {
        const response = await api.get(`/poll/community/${communityId}`, {
          headers,
        });
        if (response.status === 200) {
          const pollData = response.data.reverse();
          setPolls(pollData);
        }
      } catch (e) {
        console.log(e);
      }
    };

    fetchPolls();
  }, [communityId]);

  return (
    <div className="bg-gray-100 w-full lg:w-full md:w-screen sm:w-full font-san h-screen">
      <div className="bg-white flex flex-col pl-6 pr-7 py-6 gap-y-7">
        <div className="logo-profile-createPoll flex justify-between items-center">
          <div className="logo-text">
            <p className="whitespace-normal text-lg hidden text-gray-700 lg:block">
              Welcome to the Pollify bro
              <span className="text-blue-custom font-bold uppercase">
                {" "}
                {username}{" "}
              </span>
              !
            </p>
            <img src={PolliFy} alt="pollify" className="w-fit h-7 lg:hidden" />
          </div>
          <div className="translate flex gap-x-3 items-center lg:hidden">
            <MdTranslate className="w-6 h-6" />
            <IoMdNotificationsOutline className="w-6 h-6" />
            <div className="relative cursor-pointer" onClick={handleClick}>
              <img
                src={Avatar}
                alt="Profile 1"
                className="w-10 h-10 rounded-full mr-2 border-2 border-blue-500"
              />
              <span className="bottom-1 left-8 absolute  w-3 h-3 bg-green-400 border-2 border-white dark:border-gray-800 rounded-full"></span>
            </div>
          </div>
        </div>
        <div className="create-poll w-full flex flex-row justify-around items-center gap-x-3">
          <div className="search-field relative w-11/12 flex">
            <div className="w-full absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
              <AiOutlineSearch className="text-blue-custom w-5 h-5" />
            </div>
            <input
              type="text"
              placeholder="Search Poll"
              className="py-2 px-4 pl-9 border-2 border-gray-300 w-full rounded-full focus:outline-none focus:border-blue-500"
            />
          </div>

          <button
            onClick={handleCreatePoll}
            className="bg-blue-custom hover:opacity-70 text-white whitespace-nowrap rounded-full px-4 py-2.5"
            disabled={community.length === 0}
          >
            Create Poll
          </button>
          <div className="flex justify-center items-center border border-blue-custom rounded-full w-14 h-12">
            <img className="w-8 h-8" src={TrophyIcon} alt="Trophy" />
          </div>
        </div>
        {isCreatePollPopupOpen && <CreatePollPopup />}
      </div>
      <div className="flex flex-col gap-y-5 h-[87%] lg:h-[80%] overflow-auto p-6 home-scrolling">
        {polls.length > 0 ? (
          <div className="flex flex-col gap-y-5">
            {polls.map((poll: any) => (
              <Poll1
                key={poll.id}
                votedOn={poll.votedOn}
                pollId={poll.id}
                createdBy={poll.user.createdBy}
                pollDate={poll.user.createdAt}
                options={poll.options}
                pollQuestion={poll.pollQuestion}
              />
            ))}
          </div>
        ) : (
          <NoPoll />
        )}
      </div>
    </div>
  );
}

export default CreatePoll;
