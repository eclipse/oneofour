# How to contribute to Eclipse OneOFour

First of all, thanks for considering to contribute to Eclipse OneOFour. We really appreciate the time and effort you want to
spend helping to improve things around here.

Here is a (non-exclusive, non-prioritized) list of things you might be able to help us with:

* bug reports
* bug fixes
* improvements regarding code quality e.g. improving readability, performance, modularity etc.
* features (both ideas and code are welcome)
* tests

In order to get you started as fast as possible we need to go through some organizational issues first.

## Legal Requirements

OneOFour is an [Eclipse IoT](https://iot.eclipse.org) project and as such is governed by the Eclipse Development process.
This process helps us in creating great open source software within a safe legal framework.

### First Steps
For you as a contributor, the following preliminary steps are required in order for us to be able to accept your contribution:

* Sign the [Eclipse Contributor Agreement](https://www.eclipse.org/legal/ECA.php).
In order to do so:

  * Obtain an Eclipse Foundation user ID: [register on the Eclipse web site](https://accounts.eclipse.org/user/register).
  * Once you have your account, log in to the [user portal](https://accounts.eclipse.org/user/login), select *Eclipse Contributor Agreement*.

* Add your GiHub username to your [Eclipse Foundation account](https://accounts.eclipse.org/user/edit).

### File Headers
A proper header must be in place for any file contributed to Eclipse OneOFour. For a new contribution, please add the below header:

```
/*******************************************************************************
 * Copyright (c) <year> <legal entity> and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 *******************************************************************************/
 ```

 Please ensure \<year\> is replaced with the current year or range (e.g. 2020 or 2018, 2020).
 Please ensure \<legal entity\> is replaced with the relevant information. If you are editing an existing contribution, feel free
 to create or add your legal entity to the contributors section as such:

 ```
 /*******************************************************************************
 * Copyright (c) <year> <legal entity> and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     <legal entity>
 *******************************************************************************/
 ```

### How to Contribute
The easiest way to contribute code/patches/whatever is by creating a GitHub pull request (PR). When you do make sure that you *Sign-off* your commit records using the same email address used for your Eclipse account.

You do this by adding the `-s` flag when you make the commit(s), e.g.

    $> git commit -s -m "Descriptive and meaningful commit message"

You can find all the details in the [Contributing via Git](https://wiki.eclipse.org/Development_Resources/Contributing_via_Git) document on the Eclipse web site.

## Making your Changes

* Fork the repository on GitHub
* Create a new branch for your changes
* Make your changes
* Make sure you include test cases for non-trivial features
* Make sure the test suite passes after your changes
* Make sure copyright headers are included in (all) files including updated year(s)
* Make sure build plugins and dependencies and their versions have (approved) [Contribution Questionnaires](https://www.eclipse.org/projects/handbook/#ip-cq)
* Make sure proper headers are in place for each file (see above Legal Requirements)
* Commit your changes into that branch
* Use descriptive and meaningful commit messages
* If you have a lot of commits squash them into a single commit
* Make sure you use the `-s` flag when committing as explained above
* Push your changes to your branch in your forked repository
* Make a full clean build before you submit a pull request

## Submitting the Changes

Submit a pull request via the normal GitHub UI.

## After Submitting

Do not use your branch for any other development, otherwise further changes that you make will be visible in the PR.
