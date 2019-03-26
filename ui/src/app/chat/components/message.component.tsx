import { MessageComponentProps } from '@App/chat/models';
import { ListItem, ListItemText } from '@material-ui/core';
import React, { FunctionComponent } from 'react';

const MessageComponent: FunctionComponent<MessageComponentProps> = (props) => {
    return (
        <ListItem>
            <ListItemText primary={props.message.content}/>
        </ListItem>
    );
};

export default MessageComponent;
