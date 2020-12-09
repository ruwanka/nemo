# Nemo

Nemo is a software development task automator. Nemo expects tasks in yaml format with specific DSL. Also, nemo
is capable of automating chain of tasks. Say you have multiple maven projects in github or gitlab. Then you need to update a dependency
in all projects. Nemo can do it for you. What you need to have is following.

```yaml
name: clone github projects, add source encoding, updated spring-cloud-commons-dependencies
tasks:
  - name: git-clone
    arguments:
      username: ***********
      password: ***********
      baseDir: /workspace/nemo
  - name: git-checkout
    arguments:
      branch: TASK-01
      new: true
  - name: mvn-add-property
    arguments:
      property: "project.build.sourceEncoding"
      value: "UTF-8"
  - name: mvn-dependency-update
    arguments:
      groupId: org.springframework.cloud
      artifactId: spring-cloud-commons-dependencies
      version: 2.2.4-RELEASE
execution:
  by: git-clone
  argument: url
  repeatOn:
    - https://github.com/ruwanka/spring-cloud-config.git
```

you can have many inputs to the repeatOn. Nemo will happily automate all of them for you.

## Usage

### docker

```shell script
docker run --rm -it -v g:/examples:/tasks -v g:/nemo/ruwanka:/workspace nemo-0.1 /tasks/update-dependency.yml
```

### using executable shell script

```shell script
nemo.sh path_to_task
```

### using executable batch script

```shell script
nemo.cmd path_to_task
```

## Writing Plugins

Nemo is extendable. you can write plugins that to automate tasks and integrate with nemo.

## Development

- java 9+
- maven 3.5.3+

run build.sh file to build the project. use run.sh to run one of the examples in examples directory.

## Actions

Task that nemo can execute is an Action, that's how we call it. Each action has specified set of inputs and outputs.
For an example git-clone action has three inputs username, password and url and it has output named dir (type of File).
you can read more about git-clone action below.