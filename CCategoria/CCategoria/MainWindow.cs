using Gtk;
using MySql.Data.MySqlClient;
using System;
using System.Data;

using CCategoria;

public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        Build();

        deleteAction.Sensitive = false;

        App.Instance.Connection = new MySqlConnection("server=localhost;database=dbprueba;user=root;password=sistemas");
        App.Instance.Connection.Open();

        treeView.AppendColumn("id", new CellRendererText(), "text", 0);
        treeView.AppendColumn("nombre", new CellRendererText(), "text", 1);
        ListStore listStore = new ListStore(typeof(string), typeof(string));
        treeView.Model = listStore;

        fillListStore(listStore);

        treeView.Selection.Changed += delegate {
            deleteAction.Sensitive = treeView.Selection.CountSelectedRows() > 0;
            //if (treeView.Selection.CountSelectedRows() > 0)
            //    deleteAction.Sensitive = true;
            //else
                //deleteAction.Sensitive = false;
        };
        
        newAction.Activated += delegate {
            new CategoriaWindow();
        };

        refreshAction.Activated += delegate {
            fillListStore(listStore);
		};

        deleteAction.Activated += delegate {
            MessageDialog messageDialog = new MessageDialog(
                this,
                DialogFlags.Modal,
                MessageType.Question,
                ButtonsType.YesNo,
                "¿Quieres eliminar el registro?"
            );



            ResponseType response = (ResponseType)messageDialog.Run();
            messageDialog.Destroy();
            if (response == ResponseType.Yes) {
                TreeIter treeIter;
                treeView.Selection.GetSelected(out treeIter);
                if (treeIter.Equals(TreeIter.Zero))
                    Console.WriteLine("Ninguno seleccionado");
                else
                    Console.WriteLine("id=" + listStore.GetValue(treeIter, 0));
                //TODO eliminar
            }
            

        };
    }

    private void fillListStore(ListStore listStore) {
		listStore.Clear();
        IDbCommand dbCommnand = App.Instance.Connection.CreateCommand();
		dbCommnand.CommandText = "select * from categoria order by id";
        IDataReader dataReader = dbCommnand.ExecuteReader();
		while (dataReader.Read())
			listStore.AppendValues(dataReader["id"].ToString(), dataReader["nombre"]);
		dataReader.Close();
	}

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        App.Instance.Connection.Close();

        Application.Quit();
        a.RetVal = true;
    }
}
