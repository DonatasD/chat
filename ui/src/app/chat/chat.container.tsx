import { loadChatRequest } from '@App/chat/actions';
import MessageListComponent from '@App/chat/components/message-list.component';
import { ChatContainerProps, ChatContainerState } from '@App/chat/models';
import { initialChatState } from '@App/chat/reducers';
import setupSocket from '@Config/websocket';
import { Grid, List, ListItem, ListItemText, Theme } from '@material-ui/core';
import { createStyles, withStyles, WithStyles } from '@material-ui/styles';
import { State } from '@Reducers/index';
import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Dispatch } from 'redux';

const styles = (theme: Theme) => ({
    grid: {
        height: '80vh',
    },
    gridItem: {
        height: '10%',
        overflow: 'overlay',
    },
});

class ChatContainer extends Component<ChatContainerProps & WithStyles<typeof styles>, ChatContainerState> {

    public static defaultProps: ChatContainerProps = {
        actions: {
            dispatch: action => action,
            load: () => {
            },
        },
        chat: initialChatState,
    };

    public componentDidMount() {
        this.props.actions.load();
        this.state = {
            ws: setupSocket(this.props.actions.dispatch),
        };
        this.state.ws.onclose = ev => console.log('CLOSE', event);
    }

    public componentWillUnmount(): void {
        this.state.ws.close();
    }

    public render() {
        return (
            <Grid className={this.props.classes.grid} container spacing={8}>
                <Grid item xs={4} className={this.props.classes.gridItem}>
                    <List>
                        {[1, 2, 3, 4, 5, 6].map((a, index) => (
                            <ListItem key={index}>
                                <ListItemText primary={`Hello ${a}`}/>
                            </ListItem>
                        ))}
                    </List>
                </Grid>
                {!this.props.chat.loading && (
                    <MessageListComponent messages={this.props.chat.messages}/>
                )}
                <Grid item xs={12}>
                    <input type='text'/>
                </Grid>
            </Grid>
        );
    }
}

const mapStateToProps = (state: State) => ({
    chat: state.chat,
});

const mapDispatchToProps = (dispatch: Dispatch) => ({
    actions: {
        dispatch,
        load: () => dispatch(loadChatRequest()),
    },
});

const mapThemeToProps = (theme: Theme) => createStyles(styles(theme));

export default connect(mapStateToProps, mapDispatchToProps)(withStyles(mapThemeToProps)(ChatContainer));
