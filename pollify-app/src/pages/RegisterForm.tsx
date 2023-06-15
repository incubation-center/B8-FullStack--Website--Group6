import React, { useState } from "react";
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import { FcGoogle } from "react-icons/fc";
import pollifyLogo from "../assets/images/pollify_logo.png";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../redux/store";
import {
  setUsername,
  setEmail,
  setPassword,
  setIsAgree,
} from "../redux/slices/RegisterForm";

const RegisterForm = () => {
  const dispatch = useDispatch();
  const { username, email, password } = useSelector(
    (state: RootState) => state.register
  );
  const [showPassword, setShowPassword] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setUsername(e.target.value));
  };
  const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setEmail(e.target.value));
  };
  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setPassword(e.target.value));
  };
  const handlePasswordVisibility = (e: React.MouseEvent<HTMLButtonElement>) => {
    console.log("Click", e.target);
    // if (e.target === e.currentTarget) {
    //   setShowPassword(!showPassword);
    // }
  };
  const handleIsAgreedChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    // dispatch(setIsAgree(e.target.value));
  };
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log("Submit");
    // if (!username || !email || !password) {
    //   setErrorMessage("Please fill in all fields");
    //   return;
    // }
    // try {
    //   // Perform API call to sign up with the backend using a library like Axios or Fetch
    //   // use react-query's useMutation hook to handle the API call
    //   // Example: await signupMutation.mutateAsync({ username, email, password });
    //   // Replace `signupMutation` with your actual mutation function

    //   // Clear form inputs
    //   dispatch(setUsername(""));
    //   dispatch(setEmail(""));
    //   dispatch(setPassword(""));
    //   setErrorMessage("");
    // } catch (error) {
    //   // Handle error response from the backend
    //   setErrorMessage("Sign-up failed. Please try again.");
    // }
  };

  return (
    <div className="flex justify-center items-center h-screen">
      <form
        action=""
        onSubmit={handleSubmit}
        className="flex justify-center flex-col h-auto p-8 bg-white md:shadow-lg lg:shadow-lg"
      >
        <div className="mb-5">
          <img
            className="lg:mx-auto mb-8"
            src={pollifyLogo}
            alt="Pollify Logo"
          />
          <h1 className="text-xl font-bold text-gray-500">
            Welcome to Materio!👋🏻
          </h1>
          <span className="text-gray-500 ">
            Please register your account and start the adventure
          </span>
        </div>
        <div className="flex justify-center flex-col">
          <input
            className="border border-gray-300 rounded px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500 w-full mb-4"
            type="text"
            id="username"
            placeholder="Username"
            value={username}
            onChange={handleUsernameChange}
          />
          <input
            className="border border-gray-300 rounded px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500 w-full mb-4"
            type="email"
            placeholder="Email"
            value={email}
            onChange={handleEmailChange}
          />
          <div className="flex relative mb-2">
            <input
              className="relative border border-gray-300 rounded px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500 w-full"
              type={showPassword ? "text" : "password"}
              placeholder="Password"
              value={password}
              onChange={handlePasswordChange}
            />
            <button id="show_password" onClick={handlePasswordVisibility}>
              <AiOutlineEyeInvisible className="absolute right-4 top-4 text-gray-500 w-6 h-6" />
              {showPassword ? (
                <AiOutlineEyeInvisible className="absolute right-4 top-4 text-gray-500 w-6 h-6" />
              ) : (
                <AiOutlineEye className="absolute right-4 top-4 text-gray-500 w-6 h-6" />
              )}
            </button>
          </div>
          <div className="flex items-center ">
            <input
              type="checkbox"
              className="form-checkbox rounded mr-2 w-4 h-4"
              onChange={handleIsAgreedChange}
            />
            <span className="text-gray-500">
              I agree to privacy policy & terms
            </span>
          </div>
          <button
            id="submit"
            className="w-full text-white uppercase custom-bg-blue rounded mt-6 py-2 hover:opacity-70"
          >
            Sign Up
          </button>
          <p className="text-gray-500 mt-6 text-center">
            Already have an account?{" "}
            <a className="custom-text-blue" href="user/sign_up">
              Sign in instead
            </a>
          </p>
        </div>
        <div className="flex items-center mt-4">
          <div className="custom-line "></div>
          <p className="px-5 text-gray-500">or</p>
          <div className="custom-line "></div>
        </div>
        <div className="flex justify-center mt-6">
          <button id="google">
            <FcGoogle className="w-8 h-8" />
          </button>
        </div>
      </form>
    </div>
  );
};

export default RegisterForm;
