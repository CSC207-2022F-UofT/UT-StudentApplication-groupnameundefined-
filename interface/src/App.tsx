import React from 'react';
import { createBrowserRouter, RouterProvider, Route, Outlet, Routes } from 'react-router-dom';

import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';

import router from './router';

import { createTheme, CssBaseline, ThemeProvider, useMediaQuery } from '@mui/material';

function App() {
	const prefersDarkMode = useMediaQuery('(prefers-color-scheme: dark)');

	const theme = React.useMemo(
		() =>
			createTheme({
				palette: {
					mode: 'light',
					primary: {
						main: '#000000',
					},
					secondary: {
						main: '#4285F4',
					},
				},
			}),
		[]
	);

	return (
		<ThemeProvider theme={theme}>
			<CssBaseline />
			<RouterProvider router={router} />
		</ThemeProvider>
	);
}

export default App;
