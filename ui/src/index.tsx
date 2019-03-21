import AppRouter from '@App/app.router';
import configureStore, { history } from '@Store/configureStore';
import { ConnectedRouter } from 'connected-react-router';
import * as React from 'react';
import { render } from 'react-dom';
import { AppContainer } from 'react-hot-loader';
import { Provider } from 'react-redux';

const store = configureStore();
// store.

render(
    <AppContainer>
        <Provider store={store}>
            <ConnectedRouter history={history}>
                <AppRouter/>
            </ConnectedRouter>
        </Provider>
    </AppContainer>,
    document.getElementById('root'),
);

if (module.hot) {
    module.hot.accept('./app/app.router', () => {
        const NewAppRouter = require('./app/app.router');
        render(
            <AppContainer>
                <Provider store={store}>
                    <ConnectedRouter history={history}>
                        <NewAppRouter/>
                    </ConnectedRouter>
                </Provider>
            </AppContainer>,
            document.getElementById('root'),
        );
    });
}
