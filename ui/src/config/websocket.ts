import { messageCreated } from '@App/chat/actions';
import { ChatWebSocketEventType } from '@App/chat/models';
import { Dispatch } from 'redux';

const setupSocket = (dispatch: Dispatch) => {
    const socket = new WebSocket('ws://localhost:8080/ws/chat');
    socket.onmessage = (event) => {
        const data = JSON.parse(event.data);
        switch (data.type) {
            case ChatWebSocketEventType.MessageCreated:
                dispatch(messageCreated(data.content));
                break;
            default:
                break;
        }
    };

    return socket;
};

export default setupSocket;
