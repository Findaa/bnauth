import React, {useEffect} from 'react';
import {Typography} from '@mui/material';
import WebSocketManager from './components/WebSocketManager';
import AuthButton from "./components/Authentication";
import {Root} from "./styles/styles";

function App() {
    useEffect(() => {
        const certificateContent = process.env.SSL_CRT_FILE;
        console.log('Certificate Content: ', certificateContent);
    })
    return (

        <Root>
            <Typography variant="h5" component="h1" sx={{position: 'fixed', marginTop: '2px'}}>
                Websocket event failure reader
            </Typography>
            <AuthButton/>
            <WebSocketManager/>
        </Root>
    );
}

export default App;
