import { createMuiTheme } from '@material-ui/core/styles';

const themeOptions = {
    typography: {
        useNextVariants: true,
    },
};

function configureTheme() {
    return createMuiTheme(themeOptions);
}

export default configureTheme;
