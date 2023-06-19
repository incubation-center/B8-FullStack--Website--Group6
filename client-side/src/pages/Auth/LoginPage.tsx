import React, { useState } from "react";
import MainLogo from "../../assets/images/pollify_logo.png";
import { Link, useNavigate } from "react-router-dom";
import { FaFacebookF, FaGithub, FaTwitter } from "react-icons/fa";
import { AiOutlineEyeInvisible, AiOutlineEye } from "react-icons/ai";
import { FcGoogle } from "react-icons/fc";

const LoginForm = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const handleEmailChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const handleSignUpInstead = () => {
    navigate("/user/sign_up");
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log(`email ${email}. password ${password}`);
  };

  const handleShowPassword = () => {
    setShowPassword(!showPassword);
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 text-[#828282] font-sans">
      <form
        className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4"
        onSubmit={handleSubmit}
      >
        <div className="flex items-center justify-center mb-6">
          <img src={MainLogo} alt="Pollify logo" />
        </div>
        <div className="pb-5">
          <h2 className="text-sm font-semibold">Welcome To Materio!👋🏻</h2>
          <h6 className="text-xs">
            Please Sign-in to your account and start the adventure
          </h6>
        </div>
        <div className="mb-4">
          <input
            className="shadow-sm appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="email"
            type="email"
            placeholder="Email"
            value={email}
            onChange={handleEmailChange}
          />
        </div>
        <div className="relative m~b-6">
          <input
            className="shadow-sm appearance-none border rounded w-full py-2 px-3 pr-10 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="password"
            type={showPassword ? "text" : "password"}
            placeholder="Password"
            value={password}
            onChange={handlePasswordChange}
          />
          <button
            type="button"
            className="absolute right-0 top-0 mt-2 mr-3 text-gray-500"
            onClick={handleShowPassword}
          >
            {showPassword ? (
              <AiOutlineEyeInvisible size={20} />
            ) : (
              <AiOutlineEye size={20} />
            )}
          </button>
        </div>
        <div className="flex items-center mb-6 pt-2">
          <input
            className="mr-2 leading-tight"
            type="checkbox"
            id="rememberMe"
          />
          <label className="text-sm" htmlFor="rememberMe">
            Remember me
          </label>
          <a
            className="ml-auto text-sm text-[#2D9CDB] hover:opacity-80"
            href="#forgot"
          >
            Forgot password?
          </a>
        </div>
        <div className="flex items-center justify-between">
          <button
            className="w-full bg-[#2D9CDB] hover:opacity-80 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            type="submit"
          >
            Log In
          </button>
        </div>
        <div className="flex items-center py-2">
          <p className="pr-3 font-light text-sm">New on our platform?</p>
          <Link
            to="/user/sign_up"
            className="inline-block align-baseline font-bold text-sm text-[#2D9CDB] hover:opacity-80"
            onClick={handleSignUpInstead}
          >
            Create an account
          </Link>
        </div>
        <div className="flex items-center justify-center">
          <span className="flex-grow border-t border-gray-300 mx-2"></span>
          <p className="text-gray-400">or</p>
          <span className="flex-grow border-t border-gray-300 mx-2"></span>
        </div>
        <div className="flex items-center justify-center space-x-5 pt-6">
          <span className="text-blue-600 hover:opacity-80">
            <FaFacebookF />
          </span>
          <span className="text-gray-800 hover:opacity-80">
            <FaGithub />
          </span>
          <span className="text-blue-400 hover:opacity-80">
            <FaTwitter />
          </span>
          <span className="hover:opacity-80">
            <FcGoogle />
          </span>
        </div>
      </form>
    </div>
  );
};
export default LoginForm;
