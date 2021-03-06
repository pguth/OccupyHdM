import UIKit

class LoginViewController: UIViewController {
    @IBOutlet weak var usernameTextfield: UITextField!
    @IBOutlet weak var usernameMissingHint: UILabel!

    @IBOutlet weak var topLayoutConstraint: NSLayoutConstraint!

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        NSNotificationCenter.defaultCenter().addObserver(
            self,
            selector: #selector(LoginViewController.keyboardWillShow(_:)),
            name: UIKeyboardWillShowNotification,
            object: nil)
        NSNotificationCenter.defaultCenter().addObserver(
            self,
            selector: #selector(LoginViewController.keyboardWillHide(_:)),
            name: UIKeyboardWillHideNotification,
            object: nil)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    // MARK: - Navigation
    @IBAction func startGame (sender: AnyObject) {
        if usernameTextfield.text != "" {
            NSUserDefaults.standardUserDefaults()
                .setObject(usernameTextfield.text, forKey: "username")

            self.performSegueWithIdentifier("showLoginToMain", sender: self)
        } else {
            usernameMissingHint.hidden = false
        }
    }

    func keyboardWillShow (notification: NSNotification) {
        self.topLayoutConstraint.constant = -200
    }

    func keyboardWillHide (notification: NSNotification) {
        self.topLayoutConstraint.constant = 0
    }
}
