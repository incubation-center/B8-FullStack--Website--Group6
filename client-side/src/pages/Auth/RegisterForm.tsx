import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import { FcGoogle } from "react-icons/fc";
import { BsFacebook } from "react-icons/bs";
import pollifyLogo from "../../assets/PolliFy.png";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../redux/store";
import {
  setUsername,
  setEmail,
  setPassword,
  setIsAgree,
} from "../../redux/slices/RegisterForm";

const RegisterForm = () => {
  const dispatch = useDispatch();
  const { username, email, password, isAgree } = useSelector(
    (state: RootState) => state.register
  );
  const [showPassword, setShowPassword] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const navigate = useNavigate();

  const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setUsername(e.target.value));
  };

  const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setEmail(e.target.value));
  };

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setPassword(e.target.value));
  };

  const handlePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const handleIsAgreedChange = () => {
    dispatch(setIsAgree(!isAgree));
  };

  const handleSignInInstead = () => {
    navigate("/user/sign_in");
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log("Submit");
    if (!username || !email || !password || !isAgree) {
      setErrorMessage("Please fill in all fields");
      return;
    }
    try {
      // Perform API call to sign up with the backend

      // Clear form inputs
      dispatch(setUsername(""));
      dispatch(setEmail(""));
      dispatch(setPassword(""));
      dispatch(setIsAgree(false));
      setShowPassword(false);
      setErrorMessage("");
    } catch (error) {
      // Handle error response from the backend
      setErrorMessage("Sign-up failed. Please try again.");
    }
  };

  return (
    <div className="flex justify-center items-center h-screen">
      <form
        action=""
        onSubmit={handleSubmit}
        className="flex justify-center flex-col h-auto  text-gray-500 px-8 py-6 bg-white md:rounded-md md:shadow-lg lg:shadow-lg"
      >
        <div className="mb-5">
          <img
            className="lg:mx-auto mb-4"
            src={pollifyLogo}
            alt="Pollify Logo"
          />
          <h1 className="text-lg font-bold">Welcome to Materio!👋🏻</h1>
          <small>Please register your account and start the adventure</small>
        </div>
        <div className="flex justify-center flex-col">
          <input
            className="border text-gray-700 border-gray-300 rounded px-3 py-2 w-full text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 mb-4"
            type="text"
            id="username"
            placeholder="Username"
            value={username}
            onChange={handleUsernameChange}
          />
          <input
            className="border text-gray-700 border-gray-300 rounded px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 w-full mb-4"
            type="email"
            placeholder="Email"
            value={email}
            onChange={handleEmailChange}
          />
          <div className="flex relative mb-2">
            <input
              className="relative text-gray-700 border border-gray-300 rounded px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
              type={showPassword ? "text" : "password"}
              placeholder="Password"
              value={password}
              onChange={handlePasswordChange}
            />
            <button
              id="show_password"
              type="button"
              onClick={handlePasswordVisibility}
            >
              {showPassword ? (
                <AiOutlineEyeInvisible className="absolute right-2 top-2 text-gray-500 w-5 h-5" />
              ) : (
                <AiOutlineEye className="absolute right-2 top-2 text-gray-500 w-5 h-5" />
              )}
            </button>
          </div>
          <div className="flex flex-col">
            {errorMessage && (
              <small className="text-red-500">{errorMessage}</small>
            )}
            <div className="flex flex-row items-center text-sm">
              <input
                type="checkbox"
                checked={isAgree}
                className="form-checkbox rounded mr-2 w-4 h-4"
                onChange={handleIsAgreedChange}
              />
              <span>I agree to privacy policy & terms</span>
            </div>
          </div>
          <button
            id="submit"
            className="w-full text-white uppercase bg-blue-custom rounded mt-4 py-2 hover:opacity-70"
            disabled={!isAgree}
          >
            Sign Up
          </button>
          <span className="mt-4 text-center text-sm">
            Already have an account?{" "}
            <Link
              to="/user/sign_in"
              className="text-blue-custom hover:opacity-70"
              onClick={handleSignInInstead}
            >
              Sign in instead
            </Link>
          </span>
        </div>
        <div className="flex items-center mt-2">
          <div className="custom-line "></div>
          <p className="px-5">or</p>
          <div className="custom-line "></div>
        </div>
        <div className="flex justify-center mt-4 space-x-4">
          <button id="google">
            <FcGoogle className="w-6 h-6 hover:opacity-70" />
          </button>
          <button id="google">
            <BsFacebook className="w-5 h-5 custom-text-blue hover:opacity-70" />
          </button>
        </div>
      </form>
    </div>
  );
};

export default RegisterForm;
