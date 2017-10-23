using System;
using System.Data;

using Serpis.Ad;

namespace CCategoria
{
    public partial class CategoriaWindow : Gtk.Window
    {
        object id;
        public CategoriaWindow(object id) : this (){
            this.id = id;
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "select * from categoria where id = @id";
			DbCommandHelper.AddParameter(dbCommand, "id", id);
            IDataReader dataReader = dbCommand.ExecuteReader();
            dataReader.Read(); //TODO tratamiento de excepciones
            string nombre = (string)dataReader["nombre"];
            dataReader.Close();
            entryNombre.Text = nombre;
		}

        public CategoriaWindow() : base(Gtk.WindowType.Toplevel) {
            this.Build();

            saveAction.Activated += delegate {
                if (id == null)
                    insert();
                else
                    update();
                Destroy();
            };
        }

        private void insert() {
			string nombre = entryNombre.Text;
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "insert into categoria (nombre) values (@nombre)";
			DbCommandHelper.AddParameter(dbCommand, "nombre", nombre);
			dbCommand.ExecuteNonQuery();

		}

        private void update() {
			string nombre = entryNombre.Text;
			IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "update categoria set nombre=@nombre where id = @id";
			DbCommandHelper.AddParameter(dbCommand, "id", id);
			DbCommandHelper.AddParameter(dbCommand, "nombre", nombre);
			dbCommand.ExecuteNonQuery();
		}
    }
}
