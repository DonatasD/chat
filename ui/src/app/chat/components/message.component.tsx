import { MessageComponentProps } from '@App/chat/models';
import React, { FunctionComponent } from 'react';

const MessageComponent: FunctionComponent<MessageComponentProps> = (props) => {
    return (
        <div>
            <p>{props.message.content}</p>
        </div>
    );
};

export default MessageComponent;
