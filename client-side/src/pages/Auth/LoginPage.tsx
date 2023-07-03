import React, { useState, useEffect } from "react";
import MainLogo from "../../assets/images/pollify_logo.png";
import { Link, useNavigate } from "react-router-dom";
import { FaGithub, FaTwitter } from "react-icons/fa";
import { BsFacebook } from "react-icons/bs";
import { AiOutlineEyeInvisible, AiOutlineEye } from "react-icons/ai";
import { FcGoogle } from "react-icons/fc";

const LoginForm = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [rememberMe, setRememberMe] = useState(false);
  const [error, setError] = useState("");

  const handleUsernameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const handleSignUpInstead = () => {
    navigate("/user/sign_up");
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setError("");
    try {
      const apiUrl = "http://13.251.127.67:8080";
      const accessToken =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5YW1hIiwiZXhwIjoxNjg4NDYzOTc5fQ.FovhzFpayYtVs_1YjlVGgRZT9DR3VxnjItnTI5rEWPydNsqmwwBkqMvt64Ri0h4KpoHcr2HfAqJFu0_zFC0Ucg";

      const response = await fetch(`${apiUrl}/api/v1/auth/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${accessToken}`,
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        console.log("Login successful");
        if (rememberMe) {
          localStorage.setItem("rememberMe", "true");
        } else {
          localStorage.removeItem("rememberMe");
        }

        navigate("/community");
      } else {
        if (response.status === 401 || response.status === 403) {
          console.log("Incorrect user name or password");
          setError("Incorrect username or password!");
        } else {
          console.log("An error occurred");
          setError("An error occurred");
        }
      }
    } catch (error) {
      console.log("An Unknown error has been occurred: ", error);
    }
  };

  const handleShowPassword = () => {
    setShowPassword(!showPassword);
  };

  const handleRememberMeChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setRememberMe(event.target.checked);
  };
  useEffect(() => {
    const rememberMeValue = localStorage.getItem("rememberMe");
    setRememberMe(rememberMeValue === "true");

    if (rememberMeValue === "true") {
      navigate("/community");
    }
  }, [navigate]);

  return (
    <div className='flex flex-col items-center justify-center min-h-screen text-[#828282]'>
      <form
        className='px-8 pt-6 pb-8 mb-4 bg-white md:rounded-md md:shadow-lg lg:shadow-lg'
        onSubmit={handleSubmit}
      >
        <div className='flex items-center lg:justify-center mb-4'>
          <img src={MainLogo} alt='Pollify logo' />
        </div>
        <div className='pb-5'>
          <h2 className='text-lg font-bold'>Welcome To Materio!👋🏻</h2>
          <small>Please register your account and start the adventure</small>
        </div>
        <div>
          <input
            className='border text-gray-700 border-gray-300 rounded px-3 py-3 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 w-full mb-4'
            id='username'
            type='text'
            placeholder='Username'
            value={username}
            onChange={handleUsernameChange}
            required
          />
        </div>
        <div className='relative'>
          <input
            className='border text-gray-700 border-gray-300 rounded px-3 py-3 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 w-full'
            id='password'
            type={showPassword ? "text" : "password"}
            placeholder='Password'
            value={password}
            onChange={handlePasswordChange}
            required
          />
          <button
            type='button'
            className='absolute right-0 top-1 mt-2 mr-3 text-gray-500'
            onClick={handleShowPassword}
          >
            {showPassword ? (
              <AiOutlineEyeInvisible size={20} />
            ) : (
              <AiOutlineEye size={20} />
            )}
          </button>
        </div>
        <div className='flex items-center mb-4 pt-2'>
          <input
            className='mr-2 w-4 h-4 leading-tight'
            type='checkbox'
            id='rememberMe'
            checked={rememberMe}
            onChange={handleRememberMeChange}
          />
          <label className='text-sm' htmlFor='rememberMe'>
            Remember me
          </label>
          <a
            className='ml-auto text-sm text-[#2D9CDB] hover:opacity-80'
            href='#forgot'
          >
            Forgot password?
          </a>
        </div>
        {error && <p className='text-red-500 mb-2'>{error}</p>}

        <div className='flex items-center justify-between'>
          <button
            className='w-full uppercase bg-[#2D9CDB] hover:opacity-80 text-white py-2 px-4 rounded focus:outline-none focus:shadow-outline'
            type='submit'
          >
            Login
          </button>
        </div>
        <div className='flex items-center justify-center py-2 mt-3'>
          <p className='pr-3 font-light text-sm'>New on our platform?</p>
          <Link
            to='/user/sign_up'
            className='inline-block align-baseline font-bold text-sm text-[#2D9CDB] hover:opacity-70'
            onClick={handleSignUpInstead}
          >
            Create an account
          </Link>
        </div>
        <div className='flex items-center justify-center'>
          <span className='flex-grow border-t border-gray-300 mx-2'></span>
          <p className='text-gray-400'>or</p>
          <span className='flex-grow border-t border-gray-300 mx-2'></span>
        </div>
        <div className='flex items-center justify-center space-x-5 pt-4'>
          <span>
            <BsFacebook className='text-blue-600 w-6 h-6 hover:opacity-70' />
          </span>
          <span>
            <FaGithub className='text-gray-800 w-6 h-6 hover:opacity-70' />
          </span>
          <span>
            <FaTwitter className='text-blue-400 w-6 h-6 hover:opacity-70' />
          </span>
          <span>
            <FcGoogle className='w-6 h-6 hover:opacity-70' />
          </span>
        </div>
      </form>
    </div>
  );
};
export default LoginForm;
