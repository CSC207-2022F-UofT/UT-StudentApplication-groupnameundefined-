import { createBrowserRouter } from 'react-router-dom';
import Home from './view/Home';
import Login from './view/Auth/Login';

export default createBrowserRouter([
	{
		path: '/',
		element: <Home />,
	},
	{
		path: '/login',
		element: <Login />,
	},
]);
