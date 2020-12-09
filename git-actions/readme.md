# git-actions

### git-clone

_git-clone_ action clones a project specified by given _url_ using _username_ and _password_. It clones the project to the given
_baseDir_ location, after cloning it returns the output directory in output named _dir_

Inputs

- `url`
- `username`
- `password`
- `baseDir`

Output

- `dir` - cloned directory

### git-checkout

Inputs

- `dir`
- `branch`
- `new`

Output

- `dir` - git directory