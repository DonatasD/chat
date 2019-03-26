import MessageComponent from '@App/chat/components/message.component';
import { Message, MessageListComponentProps } from '@App/chat/models';
import { Grid, List } from '@material-ui/core';
import React, { FunctionComponent } from 'react';

const MessageListComponent: FunctionComponent<MessageListComponentProps> = ({messages = []}) => {
    return (
        <Grid item xs={8}>
            <List>
                {messages.map((message: Message, index) => (
                    <MessageComponent message={message} key={index}/>
                ))}
            </List>
        </Grid>
    );
};

export default MessageListComponent;
