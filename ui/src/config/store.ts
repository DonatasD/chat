import { isProduction } from '@Utils/environment';
import { routerMiddleware } from 'connected-react-router';
import { createBrowserHistory } from 'history';
import { applyMiddleware, compose, createStore, DeepPartial } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import reduxImmutableStateInvariant from 'redux-immutable-state-invariant';
import createSagaMiddleware from 'redux-saga';
import createRootReducer from '../reducers';
import rootSaga from '../sagas';

export const history = createBrowserHistory();
const reactRouterMiddleware = routerMiddleware(history);
const sagaMiddleware = createSagaMiddleware();
const commonMiddleware = [
    sagaMiddleware,
    reactRouterMiddleware,
];

function configureStoreProd(initialState: DeepPartial<any> = {}) {

    const middleware = [
        ...commonMiddleware,
    ];

    const store = createStore(
        createRootReducer(history),
        initialState,
        compose(applyMiddleware(...middleware)),
    );

    sagaMiddleware.run(rootSaga);
    return store;
}

function configureStoreDev(initialState: DeepPartial<any> = {}) {

    const middleware = [
        reduxImmutableStateInvariant(),
        ...commonMiddleware,
    ];

    const composeEnhancers = composeWithDevTools({});
    const store = createStore(
        createRootReducer(history),
        initialState,
        composeEnhancers(applyMiddleware(...middleware)),
    );

    if (module.hot) {
        module.hot.accept('../reducers/', () => {
            store.replaceReducer(createRootReducer(history));
        });
    }
    sagaMiddleware.run(rootSaga);
    return store;
}

const configureStore = isProduction() ? configureStoreProd : configureStoreDev;

export default configureStore;
