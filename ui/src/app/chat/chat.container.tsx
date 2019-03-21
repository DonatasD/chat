import { loadChatRequest } from '@App/chat/actions';
import MessageComponent from '@App/chat/components/message.component';
import { ChatContainerProps } from '@App/chat/models';
import { initialChatState } from '@App/chat/reducers';
import { State } from '@Reducers/index';
import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Dispatch } from 'redux';

class ChatContainer extends Component<ChatContainerProps> {

    public static defaultProps: ChatContainerProps = {
        actions: {},
        chat: initialChatState,
    };

    public componentDidMount() {

        this.props.actions.load();
    }

    public render() {
        return (
            <div>
                <h1>Chat</h1>
                {!this.props.chat.loading && this.props.chat.messages.map((message, index) => (
                    <MessageComponent message={message} key={index}/>
                ))}
            </div>
        );
    }
}

const mapStateToProps = (state: State) => ({
    chat: state.chat,
});

const mapDispatchToProps = (dispatch: Dispatch) => ({
    actions: {
        load: () => dispatch(loadChatRequest()),
    },
});
export default connect(mapStateToProps, mapDispatchToProps)(ChatContainer);
