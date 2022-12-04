import React from 'react';
import { createBrowserRouter, Outlet } from 'react-router-dom';
import Home from './view/Home';
import Login from './view/Auth/Login';
import Register from './view/Auth/Register';
import StudyPartner from './view/StudyPartner';
import TopNavigation from './component/TopNavigation';

const AppLayout = () => (
	<>
		<TopNavigation />
		<Outlet />
	</>
);

export default createBrowserRouter([
	{
		element: <AppLayout />,
		children: [
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
		],
	},
]);
