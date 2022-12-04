import React from 'react';
import { Link, LinkProps } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

export default function Logo(props: LinkProps) {
	const { sx = [], ...rootProps } = props;

	return (
		<Link
			component={RouterLink}
			to='/'
			variant='h6'
			sx={[{
				fontWeight: 700,
				letterSpacing: '.3rem',
				color: 'inherit',
				textDecoration: 'none',
			}, ...(Array.isArray(sx) ? sx : [sx])]} {...rootProps}
		>
			UT-Connect
		</Link>
	);
}