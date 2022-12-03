import React from 'react';
import { createBrowserRouter } from 'react-router-dom';
import Home from './view/Home';
import Login from './view/Auth/Login';
import Register from './view/Auth/Register';
import StudyPartner from './view/StudyPartner';

export default createBrowserRouter([
	{
		path: '/',
		element: <Home />,
	},
	{
		path: '/login',
		element: <Login />,
	},
	{
		path: '/register',
		element: <Register />,
	},
	{
		path: '/study-partner',
		element: <StudyPartner />,
	},
]);
